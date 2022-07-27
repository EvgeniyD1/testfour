<#import "parts/common.ftlh" as c>
<@c.page>
    <table class="table" id="user-data">
        <button class="btn btn-outline-info mb-3 mx-1" type="button" id="select">
            Confirm Selection
        </button>
        <form method="post" action="/users/blockAll">
            <label for="blockId"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="blockId" id="blockId" value=""/>
            <button class="btn btn-outline-info mb-3 mx-1" type="submit" id="select">
                Block All Selected Accounts
            </button>
        </form>
        <form method="post" action="/users/unblockAll">
            <label for="unblockId"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="unblockId" id="unblockId" value=""/>
            <button class="btn btn-outline-info mb-3 mx-1" type="submit" id="select">
                Unblock All Selected Accounts
            </button>
        </form>
        <form method="post" action="/users/deleteAll">
            <label for="deleteId"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="deleteId" id="deleteId" value=""/>
            <button class="btn btn-outline-info mb-3 mx-1" type="submit" id="select">
                Delete All Selected Accounts
            </button>
        </form>
        <thead class="thead-light">
        <tr>
            <th scope="col">
                <label for="checkAll"></label>
                <input type="checkbox" id="checkAll" class="checkAll" onclick="eventCheckBox(this)"/>
            </th>
            <th scope="col" class="align-middle">#</th>
            <th scope="col" class="align-middle">Username</th>
            <th scope="col" class="align-middle">Email</th>
            <th scope="col" class="align-middle">First Login</th>
            <th scope="col" class="align-middle">Last Login</th>
            <th scope="col" class="align-middle">Status</th>
            <th scope="col" class="align-middle">Not Blocked</th>
            <th scope="col" class="align-middle">Block Account</th>
            <th scope="col" class="align-middle">Unblock Account</th>
            <th scope="col" class="align-middle">Delete Account</th>
        </tr>
        </thead>
        <tbody>
        <#list users?sort_by("id")?reverse as user>
            <tr data-id="${user.id}">
                <td class="align-middle">
                    <label for="${user.id}"></label>
                    <input type="checkbox" id="${user.id}" value="${user.id}" class="check"/>
                </td>
                <td class="align-middle">${user.id}</td>
                <td class="align-middle">${user.username}</td>
                <td class="align-middle">${user.email}</td>
                <td class="align-middle">${user.creationDate?date}</td>
                <td class="align-middle">${user.loginDate?date}</td>
                <td class="align-middle">${user.status}</td>
                <td class="align-middle">${user.notBlocked?c}</td>
                <td class="align-middle">
                    <form method="post" action="/users/block/${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-outline-info ">
                            Block
                        </button>
                    </form>
                </td>
                <td class="align-middle">
                    <form method="post" action="/users/unblock/${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-outline-info ">
                            Unblock
                        </button>
                    </form>
                </td>
                <td class="align-middle">
                    <form method="post" action="/users/delete/${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-outline-info ">
                            Delete
                        </button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <script>
        function eventCheckBox(source) {
            let checkboxs = document.getElementsByClassName("check");
            for (let i = 0; i < checkboxs.length; i++) {
                if (checkboxs[i] !== source)
                    checkboxs[i].checked = source.checked;
            }
        }
        document.getElementById('select').onclick = function () {
            let checkboxes = document.getElementsByClassName("check");
            let userIds = [];
            for (let checkbox of checkboxes) {
                if (checkbox.checked) {
                    userIds.push(checkbox.value)
                }
            }
            let all = userIds.toString();
            console.log(all)
            document.getElementById('blockId').value = all;
            document.getElementById('unblockId').value = all;
            document.getElementById('deleteId').value = all;
        }
    </script>
</@c.page>