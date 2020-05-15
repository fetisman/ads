<#import "../parts/common.ftl" as c>

<@c.page>
    <#if mailSendWarning??>
        <div>
            ${mailSendWarning}
        </div>
    </#if>
</@c.page>