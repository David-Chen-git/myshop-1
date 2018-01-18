var UserAgent = function () {
    var ua = window.navigator.userAgent.toLowerCase();
    var handleIsWeChat = function () {
        if(ua.match(/MicroMessenger/i)=='micromessenger'){
            return true;
        }
        else {
            return false;
        }
    }

    return {
        isWeChat:function () {
            return handleIsWeChat();
        }
    }
}();