<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Catalog editor</h5>

    <form action="/catalog/edit" method="post">
        <div class="col-sm-10">
            <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                   name="title" value="<#if catalog??>${catalog.title}</#if>"
                   minlength="2" maxlength="20" autocomplete="off"/>
            <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
            </#if>
            <#--<div id="isTitleError" class="invalid-feedback">
                Catalog title should be min - 2 & max - 20 chars
            </div>-->
        </div>
        <input type="hidden" value="<#if catalog??>${catalog.id}</#if>" name="catalogId"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary mt-3">Save catalog</button>
    </form>
</@c.page>