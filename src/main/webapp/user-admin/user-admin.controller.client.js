(function () {

    let $usernameFld, $passwordFld;
    let $removeBtn, $editBtn, $createBtn, $updateBtn;
    let $firstNameFld, $lastNameFld, $roleFld;
    let $userRowTemplate, $tbody;
    let userService = new AdminUserServiceClient();

    let listUsers = [
        {username: 'alice', firstName: 'Alice', lastName: 'Wonderland', role: 'FACULTY'}
    ];

    let currUserId = '123';

    function main() {
        $userRowTemplate = $('#wbdv-template');
        $createBtn = $('.wbdv-create');
        $createBtn.click(() => creatUser());
        $updateBtn = $('.wbdv-update');
        $updateBtn.click(() => updateUser());
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $tbody = $('.wbdv-tbody');

        findAllUsers();
    }

    function creatUser() {
        const newUser = {
            username: $usernameFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val()
        };
        $usernameFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
        $passwordFld.val("");

        userService.createUser(newUser)
            .then((actualUser) => {
                listUsers.push(newUser);
                renderUsers();
            })
    }

    function deleteUser(userId) {
        userService.deleteUser(userId)
            .then(response => {
                findAllUsers();
            });
    }

    function selectUser(userId) {
        userService.findUserById(userId)
            .then(actualUser => {
                $usernameFld.val(actualUser.username);
                $firstNameFld.val(actualUser.firstName);
                $lastNameFld.val(actualUser.lastName);
                $roleFld.val(actualUser.role);
                currUserId = userId;
            })
    }


    function updateUser() {
        const updatedUser = {
            username: $usernameFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val()
        };

        $usernameFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
        $passwordFld.val("");

        userService.updateUser(currUserId, updatedUser)
            .then(response =>
                findAllUsers());
    }

    function findUserById(userId) {
        userService.findUserById(userId)
            .then(function (res) {
                return res;
            })
    }


    function renderUser(user) {
        const rowClone = $userRowTemplate.clone();
        rowClone.find('.wbdv-username').html(user.username);
        rowClone.find('.wbdv-first-name').html(user.firstName);
        rowClone.find('.wbdv-last-name').html(user.lastName);
        rowClone.find('.wbdv-role').html(user.role);
        rowClone.find('.wbdv-remove').attr('id', user._id);
        rowClone.find('.wbdv-edit').attr('id', user._id);

        $tbody.append(rowClone);

    }

    function renderUsers() {
        $tbody.empty();
        for (let u in listUsers) {
            let user = listUsers[u];
            renderUser(user);
        }
    }


    function findAllUsers() {
        userService
            .findAllUsers()
            .then((theUsers) => {
                listUsers = theUsers;
                renderUsers();

                $editBtn = $('.wbdv-edit');
                $editBtn.click(function () {
                    let id = $(this).attr('id');
                    selectUser(id);
                    findUserById(id);

                });
                $removeBtn = $('.wbdv-remove');
                $removeBtn.click(function () {
                    let id = $(this).attr('id');
                    deleteUser(id);
                });
            })
    }


    $(main);

})();
