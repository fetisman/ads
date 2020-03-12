<#import "parts/common.ftl" as c>
<@c.page>
    <#if isCurrentUser??>
        <#include "parts/advEdit.ftl" />
    </#if>
    <#include "parts/advList.ftl" />
</@c.page>