<#assign session = Session.SPRING_SECURITY_CONTEXT??>
<#if session>
    <#assign
    userFromSession = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = userFromSession.getUsername()
    isUser = userFromSession.isUser()
    >
<#else>
    <#assign
    isUser = false
    name = ""
    >
</#if>