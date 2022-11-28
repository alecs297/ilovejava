<div id="profile-quick-access" class="w-full px-0 lg:px-3 mb-12 lg:mb-2 text-6xl lg:text-2xl select-none">
    <div class="font-bold truncate">
        ${user.getUsername()}
    </div>
    <div class="border-l-2 border-java-pink pl-2 text-4xl lg:text-xs">
        ${user.getPoints()} points
    </div>
</div>
<div id="profile-dropdown" class="px-0 lg:px-3 lg:hidden text-5xl lg:text-xl lg:py-3">
    <ul class="py-3">
        <li class="hover:text-java-pink">
            <a href="/profile">Profile</a>
        </li>
        <li class="hover:text-java-pink">
            <a href="/account">Account</a>
        </li>
        <li class="text-red-600">
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="cursor-pointer" type="submit" value="Logout">
            </form>
        </li>
    </ul>
</div>