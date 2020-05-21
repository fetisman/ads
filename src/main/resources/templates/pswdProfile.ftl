<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Change password for ${(user.username)!''}</h5>

    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Old password : </label>
            <div class="col-sm-10">
                <input type="password" name="oldPassword"
                        class="form-control ${(oldPasswordError??)?string('is-invalid', '')}"
                        placeholder="Input your old password"
                        value="<#if oldPassword??>${oldPassword}</#if>"/>
                <#if oldPasswordError??>
                    <div class="invalid-feedback">
                        ${oldPasswordError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> New password : </label>
            <div class="col-sm-10">
                <input id="pswd" onkeyup="checkParams()"
                        type="password" name="newPassword"
                        class="form-control ${(newPasswordError??)?string('is-invalid', '')}"
                        placeholder="The new password should be min - 8 & max - 15 chars"
                        value="<#if newPassword??>${newPassword}</#if>"/>
                <#if newPasswordError??>
                    <div class="invalid-feedback">
                        ${newPasswordError}
                    </div>
                </#if>
                <div id="isPasswordError" class="invalid-feedback">
                    Password should be min - 8 & max - 15 chars !
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password confirm : </label>
            <div class="col-sm-10">
                <input id="pswd2" onkeyup="checkParams()"
                        type="password" name="confirmedPassword"
                        class="form-control ${(confirmedPasswordError??)?string('is-invalid', '')}"
                        placeholder="Retype new password" autocomplete="off"
                        value="<#if confirmedPassword??>${confirmedPassword}</#if>"/>
                <#if confirmedPasswordError??>
                    <div class="invalid-feedback">
                        ${confirmedPasswordError}
                    </div>
                </#if>
                <div id="isPswdEquals" class="invalid-feedback">
                    Passwords are different !
                </div>
            </div>
        </div>

        <button id="submit" class="btn btn-primary" type="submit" disabled>Save</button>

        <div>
            <input id="email"
                   onkeyup="checkParams()"
                   class="form-control"
                   type="hidden" name="email" value="${(user.email)!''}"/>
        </div>

        <div>
            <input id="uname"
                   onkeyup="checkParams()"
                   type="hidden" value="${(user.username)!''}"/>
            <div id="isUsernameError"></div>
        </div>

        <div>
            <input id="lastname"
                   onkeyup="checkParams()"
                   type="hidden" value="${(user.userLastName)!''}"/>
            <div id="isUserLastNameError"></div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    </form>
</@c.page>