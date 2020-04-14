<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-10">
                <input <#if isRegisterForm>id="uname" onkeyup="checkParams()" autocomplete="off"</#if>
                       type="text" name="username"
                       value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="User name" maxlength="15" autocomplete="off"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-10">
                <input <#if isRegisterForm>id="pswd" onkeyup="checkParams()" autocomplete="off"</#if>
                       type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password" maxlength="15" autocomplete="off"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Password : </label>
                <div class="col-sm-10">
                    <input id="pswd2" onkeyup="checkParams()"
                           type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Retype password" autocomplete="off"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email : </label>
                <div class="col-sm-10">
                    <input id="email" onkeyup="checkParams()" type="email" name="email"
                           value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="Email" maxlength="40" autocomplete="on"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="col-sm-2">
                <div class="g-recaptcha" data-sitekey="6LfHNc0UAAAAAGdBujWpjSvB-qRpUJQkc_PudR2L"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>

            <div class="col-sm-2 mt-3">
                <button id="submit" class="btn btn-primary" type="submit" disabled>
                    Create
                </button>
            </div>

        <#else>
            <div class="form-group row">
                <div class="col-sm-2">
                    <a href="/registration">Add new user</a>
                </div>

                <div class="col-sm-10">
                    <button class="btn btn-primary " type="submit">
                        Sign In
                    </button>
                </div>
            </div>

        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    </form>
</#macro>

<#macro logout>
    <!--Logout button :-->
    <form action="/logout" method="post">
        <#include "security.ftl" />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if user??>Sign Out<#else>Log in</#if></button>
    </form>
</#macro>