<#import "parts/common.ftl" as c>
<#import "parts/advFilter.ftl" as f>
<#include "parts/security.ftl">
<@c.page>
    <h5>Hello, ${name}</h5>
    <br>
    <div>This is the application for advertisements</div>
    <br>
    <@f.advsfilter /><#-- !!!!!!!!!!!!! -->
</@c.page>
