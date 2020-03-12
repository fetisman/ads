<#include "security.ftl">
<#import "pager.ftl" as p>

<@p.pager url page/>
<#--    <div class="table-responsive">-->
<table class="table table-striped table-bordered mt-3">
    <thead>
    <tr>
        <th scope="col">Adv Num</th>
        <th scope="col">Adv Type</th>
        <th scope="col">Short Desc</th>
        <th scope="col">Long Desc</th>
        <th scope="col">Phone</th>
        <th scope="col">Price</th>
        <th scope="col">Photo</th>
        <th scope="col">Adv Author</th>
    </tr>
    </thead>

    <tbody>
    <#list page.content as adv>
        <tr>
            <th>
                <div class="col align-self-center">
                    ${adv.id}
                </div>
            </th>

            <td><i class="col align-self-center">${(adv.advType.advType)!''}</i></td>

            <td><span class="col align-self-center">${adv.shortDesc}</span></td>

            <td>
                <#--<a class="row justify-content-center">-->
                <a class="col align-self-center">
                    <#if adv.isLongDescTooLong()>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle"
                                type="button" id="dropdownMenu2"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Desc
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <span class="dropdown-item-text">${adv.longDesc}</span>
                        </div>
                        <#else>
                            ${adv.longDesc}
                        </#if>
                </a>
            </td>

            <td>
                <a class="col align-self-center">
                    <#if adv.isPhoneTooLong()>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle"
                                type="button" id="dropdownMenu2"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Phone
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <span class="dropdown-item-text">${adv.phone}</span>
                        </div>
                        <#else>
                            ${adv.phone}
                        </#if>
                </a>
            </td>

            <td>
                <a class="col align-self-center">
                    ${adv.price}
                </a>
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
                    <#if adv.author.id == currentUserId>
                        <div>
                            <a class="btn btn-primary align-self-center"
                               href="/user-advs/${adv.author.id}?adv=${adv.id}">Edit
                            </a>
                        </div>
                    <#else>
                        <a class="col align-self-center"
                           href="/user-advs/${adv.author.id}">
                            ${adv.authorName}
                        </a>
                    </#if>
                </a>
            </td>

        </tr>
    <#else>
        <div>No advertisements</div>
    </#list>
    </tbody>
</table>
<#--    </div>-->
<@p.pager url page/>