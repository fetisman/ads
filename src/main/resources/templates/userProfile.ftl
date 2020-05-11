<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username!} user profile</h5>
<#--    ${message!}-->

    <form method="post" action="/user/user-profile">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> New name : </label>
            <div class="col-sm-10">
                <input
<#--                        id="username" onkeyup="checkNewUsername()"-->
                       type="text" name="username"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"
                       placeholder="Input new name"
                       value="<#if username??>${username}</#if>"/>
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
<#--                <div id="isNewUsernameError" class="invalid-feedback">-->
<#--                    Invalid username data !-->
<#--                </div>-->
            </div>
        </div>

        <button id="submit" onclick="this.hidden=true" class="btn btn-primary" type="submit"
<#--                disabled-->
        >Save</button>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</@c.page>