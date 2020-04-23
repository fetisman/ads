<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h5>Add new user</h5>
    ${message!}
    <@l.login "/registration" true/>
</@c.page>