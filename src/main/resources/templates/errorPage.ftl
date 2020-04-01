<#import "parts/common.ftl" as c>

<@c.page>
    <#if mailsendError??>
        <div>
            ${mailsendError}
        </div>
    </#if>
</@c.page>