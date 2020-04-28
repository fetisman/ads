<#macro advsfilter isGreetingForm=true>
    <div class="dropdown mt-1">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Search by <#if isGreetingForm>catalog<#else>type</#if>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <#if isGreetingForm>
                <#list catalogs! as catalog>
                    <form method="get" action="/main/${catalog.id!}" class="form-inline mt-1">
                        <input type="submit" name="title" class="dropdown-item"
                               value="${catalog.title!}"/>
                    </form>
                </#list>

            <#else >
                <#list advTypes! as advType>
                    <form method="get" action="/main/${catalog.id}" class="form-inline mt-1">
                        <input type="submit" name="filter" class="dropdown-item"
                               value="${advType.advType!}" />
                    </form>
                </#list>
            </#if>
        </div>
    </div>
</#macro>