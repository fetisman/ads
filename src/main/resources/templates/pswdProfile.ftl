<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${(user.username)!''} password profile</h5>

    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Old password : </label>
            <div class="col-sm-10">
                <input type="password" name="password0"
                        class="form-control ${(password0Error??)?string('is-invalid', '')}"
                        placeholder="Input your old password"
                        value="<#if password0??>${password0}</#if>"/>
                <#if password0Error??>
                    <div class="invalid-feedback">
                        ${password0Error}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> New password : </label>
            <div class="col-sm-10">
                <input id="pswd" onkeyup="checkParams()"
                        type="password" name="password"
                        class="form-control ${(passwordError??)?string('is-invalid', '')}"
                        placeholder="The new password should be min - 8 & max - 15 chars"
                        value="<#if password??>${password}</#if>"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
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
                        type="password" name="password2"
                        class="form-control ${(password2Error??)?string('is-invalid', '')}"
                        placeholder="Retype new password" autocomplete="off"
                        value="<#if password2??>${password2}</#if>"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
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