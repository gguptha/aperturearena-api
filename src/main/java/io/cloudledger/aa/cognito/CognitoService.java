package io.cloudledger.aa.cognito;

import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import io.cloudledger.aa.domain.user.UserRole;


import java.util.List;
import java.util.UUID;

public interface CognitoService {
    List<CreateGroupResult> initGroups();
    UserType createUser(String email, String name, String familyName, String temporaryPassword, UserRole role, UUID businessId);
    void updateUser(String awsCognitoId, String name, UserRole currentRole, UserRole newRole);
    UserType newUserWithTempPassword(String email, String name, String familyName, UserRole role, UUID businessId);
    UserType newUserWithPassword(String email, String name, String familyName, String temporaryPassword, UserRole role, UUID businessId);
    void addUserToGroup(String username, UserRole currentRole, UserRole newRole);
    void setNewPassword(String username, String newPassword);
}
