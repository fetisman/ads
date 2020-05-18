<#macro advTypeList>
    <#if isCatalogEmpty??>
        <h5>'${catalogTitle}' catalog. List of ad types :</h5>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>Title</th>
                <#--            <th>Catalog</th>-->
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <#list advTypes! as advType>
                <tr>
                    <td>${(advType.advType)!''}</td>
                    <#--                    <td><a href="/type/${catalog.id}">advType.catalog.title</a></td>-->
                    <td><a href="/type/${(advType.catalog.id)!0}/${advType.id}">edit</a></td>
                    <td><a href="/type/delete/${(advType.catalog.id)!0}/${advType.id}">delete</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <h5>No ad types</h5>
    </#if>
</#macro>