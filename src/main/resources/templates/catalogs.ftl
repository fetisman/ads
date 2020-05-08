<#import "parts/common.ftl" as c>
<#import "parts/catalogEdit.ftl" as ce>
<#import "parts/catalogList.ftl" as cl>

<@c.page>
    <@ce.catalogEdit true titleError!'' catalog "/catalog"/>
    <@cl.catalogList catalogs/>
</@c.page>