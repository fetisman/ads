<#import "parts/common.ftl" as c>

<@c.page>
    <#if mailsendError??>
        <div>
            ${mailsendError}
        </div>
<#--        <div>Server Error. AuthenticationFailedException. Please direct your problem to support by fetismanjava@gmail.com</div>-->
    </#if>
</@c.page>