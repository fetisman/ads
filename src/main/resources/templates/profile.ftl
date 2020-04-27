<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    ${message!}

    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-10">
                <input id="pswd" onkeyup="checkParams()"
                       placeholder="The new password should be min - 8 & max - 15 chars"
                       type="password" name="password" class="form-control"/>

                <div id="isPasswordError" class="invalid-feedback">
                    Password should be min - 8 & max - 15 chars
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-10">
                <input id="pswd2"
                       onkeyup="checkParams()"
                       type="password" name="password2"
                       class="form-control"
                       placeholder="Retype password" autocomplete="off"/>

                <div id="isPswdEquals" class="invalid-feedback">
                    Passwords are different
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email : </label>
            <div class="col-sm-10">
                <input id="email" onkeyup="checkParams()" type="email" name="email"
                class="form-control" placeholder="Email" />
            </div>
        </div>

        <button id="submit" class="btn btn-primary" type="submit" disabled>Save</button>

        <div>
            <input id="uname" onkeyup="checkParams()"
                    type="hidden" value="${username}"/>

            <div id="isUsernameError"></div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</@c.page>