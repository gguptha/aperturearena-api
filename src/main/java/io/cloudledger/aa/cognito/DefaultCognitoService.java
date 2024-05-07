//package io.cloudledger.aa.cognito;
//
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
//import com.amazonaws.services.cognitoidp.model.*;
//import io.cloudledger.aa.config.aws.AmazonProperties;
//import io.cloudledger.aa.domain.user.UserRole;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Lazy
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class DefaultCognitoService implements CognitoService{
//    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;
//    private final AmazonProperties amazonProperties;
//
//    @Override
//    public void addUserToGroup(String user, UserRole currentRole, UserRole newRole)
//    {
//        // Remove the user from the current group
//        if (currentRole != null) {
//            AdminRemoveUserFromGroupRequest removeRequest = new AdminRemoveUserFromGroupRequest()
//                    .withGroupName(currentRole.name())
//                    .withUserPoolId(amazonProperties.getCognito().getUserPoolId())
//                    .withUsername(user);
//            AdminRemoveUserFromGroupResult removeResult =
//                    awsCognitoIdentityProvider.adminRemoveUserFromGroup(removeRequest);
//            log.info("User remove from group {}", currentRole.name());
//        }
//
//        // Add user to the new group
//        if(newRole == null)
//            newRole = UserRole.ROLE_User;
//        AdminAddUserToGroupRequest addUserToGroupRequest = new AdminAddUserToGroupRequest()
//                .withGroupName(newRole.name())
//                .withUserPoolId(amazonProperties.getCognito().getUserPoolId())
//                .withUsername(user);
//        AdminAddUserToGroupResult adminAddUserToGroupResult =
//                awsCognitoIdentityProvider.adminAddUserToGroup(addUserToGroupRequest);
//        log.info("User  added to {}", newRole.toString());
//    }
//
//    @Override
//    public void setNewPassword(String username, String newPassword) {
//        AdminSetUserPasswordRequest request =
//                new AdminSetUserPasswordRequest()
//                        .withPassword(newPassword)
//                        .withUsername(username)
//                        .withPermanent(true)
//                        .withUserPoolId(amazonProperties.getCognito().getUserPoolId());
//        AdminSetUserPasswordResult result = awsCognitoIdentityProvider.adminSetUserPassword(request);
//    }
//
//    @Override
//    public List<CreateGroupResult> initGroups(){
//        List<UserRole> types = Arrays.asList(UserRole.values());
//        List<CreateGroupResult> results;
//
//        // Fetch existing groups
//        ListGroupsRequest listGroupsRequest = new ListGroupsRequest()
//                .withUserPoolId(amazonProperties.getCognito().getUserPoolId());
//        ListGroupsResult listGroupsResult = awsCognitoIdentityProvider.listGroups(listGroupsRequest);
//        // Transform to group names
//        List<String> existingGroupNames = listGroupsResult.getGroups().stream()
//                .map(groupType -> groupType.getGroupName())
//                .collect(Collectors.toList());
//
//        results = types.parallelStream()
//                .filter(type -> !existingGroupNames.contains(type.name())) // Filter out existing groups
//                .map(type -> {
//                    CreateGroupResult result = null;
//                    try {
//                        CreateGroupRequest createGroupRequest = new CreateGroupRequest()
//                                .withGroupName(type.name())
//                                .withDescription(type.name())
//                                .withUserPoolId(amazonProperties.getCognito().getUserPoolId());
//                        result = awsCognitoIdentityProvider.createGroup(createGroupRequest);
//                        log.info("Added group: {}", result.getGroup().getGroupName());
//                    } catch (GroupExistsException e) {
//                        log.error("{}", e.getMessage());
//                    } finally {
//                        return result;
//                    }
//
//                })
//                .filter(createGroupResult -> createGroupResult != null)
//                .collect(Collectors.toList());
//
//        return results;
//
//    }
//
//    @Override
//    public UserType createUser(String email, String name, String familyName, String password, UserRole role, UUID businessId) {
//        UserType cognitoUser = null;
//        try {
//
//            AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
//                    .withUserPoolId(amazonProperties.getCognito().getUserPoolId())
//                    .withUsername(name)
//                    .withUserAttributes(
//                            new AttributeType()
//                                    .withName("email")
//                                    .withValue(email),
//                            new AttributeType()
//                                    .withName("name")
//                                    .withValue(name),
//                            new AttributeType()
//                                    .withName("family_name")
//                                    .withValue(familyName),
//                            new AttributeType()
//                                    .withName("phone_number_verified")
//                                    .withValue("false"),
//                            new AttributeType()
//                                    .withName("email_verified")
//                                    .withValue("true")
//                    )
//                    .withTemporaryPassword(password)
//                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
//                    .withMessageAction(MessageActionType.SUPPRESS)
//                    .withForceAliasCreation(Boolean.FALSE);
//            AdminCreateUserResult createUserResult = awsCognitoIdentityProvider.adminCreateUser(cognitoRequest);
//            cognitoUser = createUserResult.getUser();
//
//            addUserToGroup(cognitoUser.getUsername(), null, role);
//            log.info("User Created in Cognito : {}",email);
//        } catch (UsernameExistsException O_o) {
//            log.info("User: {} exist(s)", email);
//            throw O_o;
//        }
//        return cognitoUser;
//    }
//
//    @Override
//    public void updateUser(String awsCognitoId, String name, UserRole currentRole, UserRole newRole) {
//        try {
//            AdminUpdateUserAttributesRequest request = new AdminUpdateUserAttributesRequest()
//                    .withUserPoolId(amazonProperties.getCognito().getUserPoolId())
//                    .withUserAttributes(
//                            new AttributeType()
//                                    .withName("name")
//                                    .withValue(name)
//                    )
//                    .withUsername(awsCognitoId);
//            AdminUpdateUserAttributesResult result = awsCognitoIdentityProvider.adminUpdateUserAttributes(request);
//            if (!currentRole.name().equals(newRole.name()))
//                addUserToGroup(awsCognitoId, currentRole, newRole);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public UserType newUserWithTempPassword(String email, String name, String familyName, UserRole role, UUID businessId) {
//        return createUser(email, name, familyName,"Temp@123", role, businessId);
//    }
//
//    @Override
//    public UserType newUserWithPassword(String email, String name, String familyName, String password, UserRole role, UUID businessId) {
//        UserType userType =  createUser(email, email, familyName, password, role, businessId);
//        if (userType != null) {
////            final Map<String, String> authParams = new HashMap<>();
////            authParams.put("USERNAME", userType.getUsername());
////            authParams.put("PASSWORD", password);
////            final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
////            authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
////                    .withClientId(amazonProperties.getCognito().getClientId())
////                    .withUserPoolId(amazonProperties.getCognito().getUserPoolId())
////                    .withAuthParameters(authParams);
////            AdminInitiateAuthResult authResult = awsCognitoIdentityProvider.adminInitiateAuth(authRequest);
////            Map<String,String> challengeResponses = new HashMap<>();
////            challengeResponses.put("USERNAME", userType.getUsername());
////            challengeResponses.put("NEW_PASSWORD", password);
////            RespondToAuthChallengeRequest respondToAuthChallengeRequest = new RespondToAuthChallengeRequest()
////                    .withChallengeName("NEW_PASSWORD_REQUIRED")
////                    .withClientId(amazonProperties.getCognito().getClientId())
////                    .withChallengeResponses(challengeResponses)
////                    .withSession(authResult.getSession());
////            awsCognitoIdentityProvider.respondToAuthChallenge(respondToAuthChallengeRequest);
//            return userType;
//        }
//        return null;
//    }
//}
