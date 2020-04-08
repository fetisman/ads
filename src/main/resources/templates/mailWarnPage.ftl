<#import "parts/common.ftl" as c>

<@c.page>
    <#if mailsendWarning??>
        <div>
            ${mailsendWarning}
        </div>
    </#if>
</@c.page>