<#include "security.ftl">
<#import "pager.ftl" as p>
<#import "dropdownAdText.ftl" as d>

<#if page.getTotalPages() gt 0>
    <@p.pager url page/>
<#--    <div class="table-responsive">-->
    <table class="table table-striped table-bordered mt-3">
        <thead>
        <tr>
            <th scope="col">Ad Num</th>
            <th scope="col">Ad Type</th>
            <th scope="col">Short Desc</th>
            <th scope="col">Long Desc</th>
            <th scope="col">Phone</th>
            <th scope="col">Price</th>
            <th scope="col">Photo</th>
            <th scope="col">Action</th>
        </tr>
        </thead>

        <tbody>
        <#list page.content as adv>
            <tr>
                <th>
                    <i class="col align-self-center">
                        <div class="row justify-content-center">
                            ${adv.id}
                        </div>
                    </i>
                </th>

                <td>
                    <i class="col align-self-center">
                        <div class="row justify-content-center">
                            ${(adv.advType.advType)!''}
                        </div>
                    </i>
                </td>

                <td>
                    <@d.dropdown adv adv.shortDesc 'Short desc'/>
                </td>

                <td>
                    <@d.dropdown adv adv.longDesc 'Long desc'/>
                </td>

                <td>
                    <@d.dropdown adv adv.phone 'Phone'/>
                </td>

                <td>
                    <@d.dropdown adv adv.price 'Price'/>
                </td>

                <td>
                    <#if adv.filename??>
                        <a class="col align-self-center">
                            <img src="/img/${adv.filename}" width="80" height="80">
                        </a>
                    </#if>
                </td>
                <td>
                    <a class="col align-self-center">
                        <#if adv.author.id == currentUserId || isAdmin>

                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${adv.authorName}
                                </a>

                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <#if !isAdmin>
                                        <a class="dropdown-item" href="/user-advs/${adv.author.id}?adv=${adv.id}">Edit</a>
                                    </#if>
                                    <a class="dropdown-item" href="/delete/${adv.id}">Delete</a>
                                    <#if isAdmin>
                                        <a class="dropdown-item" href="/user-advs/${adv.author.id}">User page</a>
                                    </#if>
                                </div>
                            </div>

                        <#else>
                            <div class="col align-self-center">
                                <a class="btn btn-secondary"
                                   href="/user-advs/${adv.author.id}">
                                    ${adv.authorName}
                                </a>
                            </div>
                        </#if>
                    </a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
<#--    </div>-->
    <@p.pager url page/>
<#else>
    <div>No advertisements</div>
</#if>