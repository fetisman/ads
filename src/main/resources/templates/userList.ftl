<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h5>List of users</h5>
    <table class="table table-striped table-bordered mt-3">
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
            <#if user.id != currentUserId>
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