<#macro dropdown adv text='' title=''>
    <a class="col align-self-center">
        <#if adv.isStringTooLong(text)>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle"
                        type="button" id="dropdownMenu1"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${title}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <span class="dropdown-item-text">${text}</span>
                </div>
            </div>
        <#else>
            <div class="row justify-content-center">
                ${text}
            </div>
        </#if>
    </a>
</#macro>