<#macro catalogEdit isCatalogs catalog path>
    <#assign
    isCatalogChosen = false
    catalogId = (catalog.id)!-1>
        <#if catalogId gt 0>
            <#assign
            isCatalogChosen = true>
            <#else>
        </#if>

<#if isCatalogs>
    <a class="btn btn-primary mt-4 mb-4" data-toggle="collapse"
       href="#collapseCatalogs" role="button" aria-expanded="false"
       aria-controls="collapseCatalogs">
        Add catalog
    </a>
</#if>

    <div class="collapse <#if (catalog?? && isCatalogChosen) || titleError??>show</#if>"
<#--    <div class="collapse <#if catalog??>show</#if>"-->
<#--    <div class="collapse <#if !isEditForm>show</#if>"-->
         id="collapseCatalogs">
        <div class="form-group mt-3">
            <form action="${path}" method="post">
                <div class="form-group">
                    <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                           name="title"
                    <#if isCatalogs>
                        placeholder="The new catalog title should be min - 2 & max - 20 chars"
                            <#else>
                                value="<#if catalog??>${(catalog.title)!''}<#else>''</#if>"
                                minlength="2" maxlength="20" autocomplete="off"
                            </#if>/>
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <#if !isCatalogs>
                    <input type="hidden" value="<#if catalog??>${(catalog.id)!-1}</#if>" name="catalogId"/>
                </#if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary mt-3">Save catalog</button>
                </div>
            </form>
        </div>
    </div>
    </#macro>