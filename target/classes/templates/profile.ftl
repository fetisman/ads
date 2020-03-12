<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    ${message!}

    <form method="post">

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email : </label>
                <div class="col-sm-10">
                    <input type="email" name="email" class="form-control" placeholder="Email" value="${email!''}" />
                </div>
            </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>