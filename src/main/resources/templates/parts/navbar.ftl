<#import "login.ftl" as l>
<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>

            <#if user??>
            <li class="nav-item active">
                <a class="nav-link" href="/main/${catalog!0}">Ads</a>
            </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/user-advs/${currentUserId}">My ads</a>
                </li>
            </#if>

            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user">User List</a>
                </li>
            </#if>

            <#if user??>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" id="collapseProfile" role="button"
                       data-toggle="dropdown" aria-expanded="false" aria-haspopup="true">
                        Profile
                    </a>

                    <div class="dropdown-menu" aria-labelledby="collapseProfile">
                    <a class="dropdown-item" href="/user/user-profile">User profile</a>
                    <a class="dropdown-item" href="/user/pswd-profile">Change password</a>
                    </div>
                </li>
            </#if>

            <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
            <@l.logout />
        </ul>
    </div>
</nav>