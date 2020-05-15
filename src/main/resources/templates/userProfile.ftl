<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Name : ${(user.username)!''}</h5>
    <h5>Email : ${(user.email)!''}</h5>

    <form method="post" action="/user/user-profile">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">
                <h5>Last name : ${(user.userLastName)!'none'}</h5>
            </label>
            <div class="col-sm-10">
                <input type="text" name="userLastName"
                       class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                       placeholder="Input new last name"
                       minlength="2" maxlength="15"/>
                <#if lastNameError??>
                    <div class="invalid-feedback">
                        ${lastNameError}
                    </div>
                </#if>
            </div>
        </div>

        <button id="submit" class="btn btn-primary" type="submit">Save</button>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</@c.page>