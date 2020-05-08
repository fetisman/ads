<#macro catalogList catalogs>
<h5>List of catalogs</h5>
    <table class="table table-striped table-bordered mt-3">
    <thead>
    <tr>
        <th>Title</th>
        <th>Types</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <#list catalogs! as catalog>
        <#if catalog.id != 0>
    <tr>
        <td>${(catalog.title)!''}</td>
        <td><a href="/type/${catalog.id}">types</a></td>
        <td><a href="/catalog/${catalog.id}">edit</a></td>
        <td><a href="/catalog/delete/${catalog.id}">delete</a></td>
    </tr>
        </#if>
    </#list>
    </tbody>
</table>
</#macro>