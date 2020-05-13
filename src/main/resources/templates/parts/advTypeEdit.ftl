<#macro advTypeEdit isAdvTypes path advTypeError advType>

    <#if isAdvTypes>
        <a class="btn btn-primary mt-4 mb-4" data-toggle="collapse"
           href="#collapseAdvTypes" role="button" aria-expanded="false"
           aria-controls="collapseAdvTypes">
            Add ad type
        </a>
    </#if>

<#--    <div class="collapse <#if advType?? || advTypeError??>show</#if>"-->
    <div class="collapse <#if !isAdvTypes || advTypeError!=''>show</#if>"
         id="collapseAdvTypes">
        <div class="form-group mt-3">
            <form action="${path}" method="post">
                <div class="form-group">
                    <input type="text" class="form-control ${(advTypeError!='')?string('is-invalid', '')}"
                           name="advTypeTitle"
                            <#if isAdvTypes>
                                placeholder="The new ad type should be min - 2 & max - 20 chars"
                            <#else>
                                value="<#if advType??>${(advType.advType)!''}</#if>"
                            </#if>
                           minlength="2" maxlength="20" autocomplete="off"/>

                    <#if advTypeError??>
                        <div class="invalid-feedback">
                            ${advTypeError}
                        </div>
                    </#if>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <#if !isAdvTypes>
                    <input type="hidden" value="<#if advType??>${(advType.id)!-1}</#if>" name="advTypeId"/>
                <#else>
                <#--                    <input type="hidden" value="<#if catalogId??>${catalogId!-1}</#if>" name="catalogId"/>-->
                </#if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary mt-3">Save ad type</button>
                </div>
            </form>
        </div>
    </div>
</#macro>