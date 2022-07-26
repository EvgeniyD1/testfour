<#include "security.ftlh">
<#macro login path isRegisterForm>
    <form method="post" action="${path}" class="">
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label" for="username">User Name:</label>
            <div class="col-sm-5 my-1">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder="username" id="username" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label" for="password">Password:</label>
            <div class="col-sm-5 my-1">
                <input type="password" name="password" class="form-control" placeholder="password" id="password"/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row justify-content-center">
                <label class="col-sm-2 col-form-label" for="email">Email:</label>
                <div class="col-sm-5 my-1">
                    <input type="email" name="email" class="form-control" placeholder="email@email.com" id="email"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="d-flex justify-content-center my-2">
            <button type="submit" class="btn btn-outline-info btn-block w-50">
                <#if isRegisterForm>
                    Create
                <#else>Sign in
                </#if>
            </button>
        </div>
        <#if !isRegisterForm>
            <div class="d-flex justify-content-center my-2">
                <form>
                    <input class="btn btn-outline-info btn-block w-50" type="button" value="Create an account"
                           onClick='location.href="/registration"'/>
                </form>
            </div>
        </#if>
    </form>
</#macro>
