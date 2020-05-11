<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username!} email profile</h5>
<#--    ${message!}-->

    <form method="post" action="/user/email-profile">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Current Email : </label>
            <div class="col-sm-10">
                <input id="email" onkeyup="checkNewEmail()"
                       type="email" name="email"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       placeholder="Input current email"
                       value="<#if email??>${email}</#if>"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
                <div id="isEmailError" class="invalid-feedback">
                    Invalid mail data !
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> New Email : </label>
            <div class="col-sm-10">
                <input id="newEmail" onkeyup="checkNewEmail()"
                       type="email" name="newEmail"
                       class="form-control ${(newEmailError??)?string('is-invalid', '')}"
                       placeholder="Input new email" autocomplete="off"/>
                <#if newEmailError??>
                    <div class="invalid-feedback">
                        ${newEmailError}
                    </div>
                </#if>
                <div id="isNewEmailError1" class="invalid-feedback">
                    Invalid new mail data !
                </div>
                <div id="isNewEmailError2" class="invalid-feedback">
                    New email already exists !
                </div>
            </div>
        </div>

        <button id="submit" onclick="this.hidden=true" class="btn btn-primary" type="submit" disabled>Save</button>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</@c.page>