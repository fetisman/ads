<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username!} password profile</h5>
<#--    ${message!}-->

    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
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
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-10">
                <input id="pswd2" onkeyup="checkParams()"
                        type="password" name="password2"
                        class="form-control ${(password2Error??)?string('is-invalid', '')}"
                        placeholder="Retype password" autocomplete="off"
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
                   type="hidden" name="email" value="${email!''}"/>
        </div>

        <div>
            <input id="uname"
                   onkeyup="checkParams()"
                   type="hidden" value="${username!}"/>
            <div id="isUsernameError"></div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    </form>
</@c.page>