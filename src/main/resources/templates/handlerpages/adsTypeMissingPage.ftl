<#import "../parts/common.ftl" as c>

<@c.page>
    <#if adsTypeMissingWarning??>
        <div>
            ${adsTypeMissingWarning}
        </div>
    </#if>
</@c.page>