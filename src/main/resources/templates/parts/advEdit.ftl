<#if !isCurrentUser??>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Adv editor
    </a>
</#if>
<div class="collapse <#if adv??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text" class="form-control ${(shortDescError??)?string('is-invalid', '')}"
                       value="<#if adv??>${adv.shortDesc}</#if>"
                       name="shortDesc" placeholder="Input short description"/>
                <#if shortDescError??>
                    <div class="invalid-feedback">
                        ${shortDescError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control ${(longDescError??)?string('is-invalid', '')}"
                       value="<#if adv??>${adv.longDesc}</#if>"
                       name="longDesc" placeholder="Input long description"/>
                <#if longDescError??>
                    <div class="invalid-feedback">
                        ${longDescError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="tel" class="form-control ${(phoneError??)?string('is-invalid', '')}"
                       value="<#if adv??>${adv.phone}</#if>"
                       name="phone" placeholder="Input your phone number"/>
                <#if phoneError??>
                    <div class="invalid-feedback">
                        ${phoneError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if adv??>${adv.price}</#if>"
                       name="price" placeholder="Input price"/>
            </div>

            <div class="form-group">

<#--                    <input type="text" class="form-control ${(typeError??)?string('is-invalid', '')}"-->
<#--                           value="<#if adv??>${(adv.advType.advType)!''}</#if>"-->
<#--                            &lt;#&ndash;                        директиву <#if adv??> вынес наружу тега <div ...>&ndash;&gt;-->
<#--                           name="type" placeholder="Choose adv type"/>-->
<#--                    <#if typeError??>-->
<#--                        <div class="invalid-feedback">-->
<#--                            ${typeError}-->
<#--                        </div>-->
<#--                    </#if>-->

                        <select class="custom-select col" name="type">
                            <#list advTypes! as advType>
                                <option value="${advType.advType!}">${advType.advType}</option>
                            </#list>
                        </select>
                </div>

            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if adv??>${adv.id!-1}</#if>"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary mt-3">Save adv</button>
            </div>
        </form>
    </div>
</div>