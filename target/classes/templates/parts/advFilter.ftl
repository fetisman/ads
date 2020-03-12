<#macro advsfilter isGreetingForm=true>
    <div class="form-row">
        <div class="form-group col-md-4 mt-1">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <a class="navbar-brand" href="#">Search by <#if isGreetingForm>catalog<#else>type</#if></a>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> ... </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <#if isGreetingForm> <#-- !!!!!!!!!!!!! -->
                                <#list catalogs! as catalog>

                                    <form method="get" action="/main/${catalog.id!}" class="form-inline mt-1">
                                        <input type="submit" name="title" class="dropdown-item"
                                               value="${catalog.title!}"/>
                                    </form>
                                </#list>


<#--                                <table>-->
<#--                                    <tbody>-->
<#--                                    <#list catalogs! as catalog>-->
<#--                                        <tr>-->
<#--                                            <td>-->
<#--                                                <a href="/main/${catalog.id!}">${catalog.title!}</a>-->
<#--                                            </td>-->
<#--                                        </tr>-->
<#--                                    </#list>-->
<#--                                    </tbody>-->
<#--                                </table>-->

                            <#else >
                                <#list advTypes! as advType>
                                    <form method="get" action="/main/${catalog.id}" class="form-inline mt-1">
                                    <input type="submit" name="filter" class="dropdown-item"
                                           value="${advType.advType!}" />
                                </form>
                            </#list>
                            </#if>
                        </div>
                    </li>
                </ul>
            </nav>
     </div>
    </div>
</#macro>