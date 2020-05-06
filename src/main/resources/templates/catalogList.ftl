<#import "parts/common.ftl" as c>
<@c.page>
    <a class="btn btn-primary mt-4 mb-4" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add catalog
    </a>

<div class="collapse <#if titleError??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
    <form action="/catalog" method="post">
        <div class="form-group">
            <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                   name="title" placeholder="The new catalog title should be min - 2 & max - 20 chars"/>
            <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
            </#if>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="form-group">
            <button type="submit" class="btn btn-primary mt-3">Save catalog</button>
        </div>
    </form>
    </div>
</div>
<#--    ------------------->
    <h5>List of catalogs</h5>
    <table class="table table-striped table-bordered mt-3">
    <thead>
    <tr>
        <th>Title</th>
        <th>Types</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <#list catalogs! as catalog>
        <#if catalog.id != 0>
    <tr>
        <td>${(catalog.title)!''}</td>
        <td><a href="/type/${catalog.id}">types</a></td>
        <td><a href="/catalog/${catalog.id}">edit</a></td>
    </tr>
        </#if>
    </#list>
    </tbody>
</table>
</@c.page>