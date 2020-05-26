<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Name : ${(user.username)!''}</h5>
    <h5>Email : ${(user.email)!''}</h5>

    <form method="post" action="/user/user-profile">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <h5>First name : ${(user.userFirstName)!'none'}</h5>
            </label>
            <div class="col-sm-10">
                <input id="newFirstName" onkeyup="checkUserProfileParams()"
                        type="text" name="newFirstName"
                        class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                        <#if user.userFirstName??>
                            value="${user.userFirstName}"
                        <#else>
                            placeholder="Input your first name"
                        </#if>
                        minlength="2" maxlength="15"/>

                <#if firstNameError??>
                    <div class="invalid-feedback">
                        ${firstNameError}
                    </div>
                </#if>

                <div id="isNewFirstNameError" class="invalid-feedback">
                    Old first name wasn't changed
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <h5>Last name : ${(user.userLastName)!'none'}</h5>
            </label>
            <div class="col-sm-10">
                <input id="newLastName" onkeyup="checkUserProfileParams()"
                        type="text" name="newLastName"
                        class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                        <#if user.userLastName??>
                            value="${user.userLastName}"
                        <#else>
                            placeholder="Input your last name"
                        </#if>
                        minlength="2" maxlength="15"/>

                <#if lastNameError??>
                    <div class="invalid-feedback">
                        ${lastNameError}
                    </div>
                </#if>

                <div id="isNewLastNameError" class="invalid-feedback">
                    Old last name wasn't changed
                </div>
            </div>
        </div>

        <button id="submit" class="btn btn-primary" type="submit" disabled>Save</button>

        <input id="currentFirstName" type="hidden" value="${(user.userFirstName)!''}"/>

        <input id="currentLastName" type="hidden" value="${(user.userLastName)!''}"/>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</@c.page>