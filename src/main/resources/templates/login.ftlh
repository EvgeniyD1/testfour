<#import "parts/common.ftlh" as c>
<#import "parts/regAndLogForm.ftlh" as l>
<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger my-3 d-flex justify-content-center">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
        <@l.login "/login" false/>
</@c.page>