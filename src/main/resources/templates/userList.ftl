<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div>List of users</div>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Roles</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <#if user.id != currentUser.id>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}"><#if currentUserId != user.id>edit</#if></a></td>
                <td><a href="/user/delete/${user.id}"><#if !user.isAdmin()>delete</#if></a></td>
            </tr>
            </#if>
        </#list>
        </tbody>
    </table>
</@c.page>