<#import "parts/common.ftl" as c>
<#import "parts/advTypeEdit.ftl" as ate>
<#import "parts/advTypeList.ftl" as atl>

<@c.page>
    <@ate.advTypeEdit false "/type/edit" advTypeError!'' advType!''/>
    <@atl.advTypeList/>
</@c.page>