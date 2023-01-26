//홈에 진입했을 때 로그인한 상태일 때 결제회원인지 알아내는 함수
function checkMembershipMember() {
    //이 멤버가 어떤 상태의 멤버인지 코드있어야 함
    //프론트 : ajax 통신, 백 : 회원결제정보 api
    let data;
//    data : notLoggedIn(로그인하지않은 회원) , paid(결제회원), notPaid(비결제회원)
//    ajax 통신
    data = 'notLoggedIn';
    if (data === 'paid') {
        //    결제 회원이면 모달 안 보여줌
        document.getElementById('login_toast_modal').style.display = "none";
    } else if (data === 'notPaid') {
        //비결제 회원이면 멤버십 정보페이지로 이동
        goToMembershipInfo();
    } else {
        document.getElementById('login_toast_modal').style.display = "block";
    }
}


//홈페이지로 이동
function goToHome() {
    location.href = "/home";
}

//로그인페이지로 이동
function goToLogin() {
    location.href = '/login'
}

//멤버십 정보 페이지로 이동
function goToMembershipInfo() {
    location.href = "/membership"
}

//unix 시간을 인간이 읽을 수 있는 형태로 변환 (날짜와 시간)
function formatTimestamp(timestamp) {
    var date = new Date(timestamp * 1000);
    var formattedDate = date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
    return formattedDate;
}
<!--TODO 서버쪽에서 자동 uid 생성 코드 있어야 함-->
//'/payment'에 request 하는 함수
let pingbackSig;
function requestSignature(){
    console.log('sig값을 받습니다')
    //sig값 받는 통신
    $.ajax({
        type: "get",
        url: "/payment/signature",
        data: {
            uid: 'user40012',
            goodsid: 'patron1',
            slength: 1,
            speriod: 'month',
            type: 0,
            ref: 't' + Math.floor(Date.now() / 1000),
            is_test: 1,
            sign_version: 3
        },
        success: function (res) {
            console.log('사인값 : ', res);
            pingbackSig = res;
            console.log('pingbackSig 사인값 : ', res);
        }
    })
}
function paymentRequest() {
    console.log('iframe에 src값을 넣습니다')
    //서버에서 parse 가능한 형태로 보내줘야하기 때문에 json 형태로 전송한다
    let json = {
        widget: 'pw_1',
        email: email,
        history: registrationDate,
        amount: amount.toString(),
        currencyCode: 'USD',
        ag_name: productName,
        ag_external_id: productId,
        ag_period_length: 1,
        ag_recurring: true,
        ag_trial: 1,
        post_trial_amount: amount,
        post_trial_currencyCode: 'USD',
        ag_post_trial_name: productName,
        ag_post_trial_external_id: productId,
        ag_post_trial_period_type: 'month',
        ag_post_trial_period_length: 1,
        ps: 'all',
        sign_version: '3',
    }
    let jsonString = JSON.stringify(json);
    $.ajax({
        type: "post",
        url: "/payment",
        data: jsonString,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (res) {
            alert("통신 성공!")
            console.log('payment페이지 url :', res.url);
            //통신이 성공하면 widget url을 받고
            // iframe src에 url을 넣어 결제창을 보여준다
            document.getElementById('payment_frame').setAttribute('src',res.url);
        },
        error: function (error) {
            console.log(error)
        }
    });
}
//pingback 요청
function requestPingback() {
    console.log("핑백을 요청함수를 실행합니다.");
    // let json = {
    //     uid: 'test_user',
    //     goodsid: 'patron_basic',
    //     slength: 1,
    //     speriod: 'month',
    //     type: 0,
    //     ref: 't12345',
    //     is_test: 1,
    //     sign_version: 3
    // }
    // let jsonString = JSON.stringify(json);
    // console.log('after signUrl:',signUrl);
    // $.ajax({
    //     type: "post",
    //     url: "/payment/pingback",
    //     data: jsonString,
    //     contentType: "application/json; charset=utf-8",
    //     dataType: "json",
    //     success: function (res) {
    //         console.log(res)
    //     },
    //     error: function (error) {
    //         console.log(error)
    //     }
    // });
    //pingback 통신
    let pingbackQueryString = `goodsid=patron1&is_test=1&ref=user40012&sign_version=3&slength=1&speriod=month&type=0&uid=user40012&sig=${pingbackSig}`
    console.log('핑백요청을 시작합니다 /payment/pingback/get')
    console.log('핑백요청을 시작합니다', pingbackSig)
    $.ajax({
        type: "get",
        url: "/payment/pingback/get?"+pingbackQueryString,
        data: {
        },
        success: function (res) {
            console.log('pingback response :',res)
        },
        error: function (error) {
            console.log(error)
        }
    });

}

//'/payment' post 통신에 보낼 파라미터 값들을 설정한다.
// 멤버십 상품에 따라 달라지는 파라미터는 setPaymentParameters(membership)를 통해
//설정된다.
// TODO 프론트 이메일 코드 있어야 함
let email = "test@gmail.com";
// TODO 서버쪽에서 amount 값 있어야 함
let amount;
let registrationDate;
let productName;
let productId;

function setPaymentParameters(membership) {
    console.log('파라미터값을 설정합니다');
    registrationDate = Math.floor(Date.now() / 1000);
    if (membership === 'basic') {
        amount = 1.00;
        productName = 'basic';
        productId = 'patron1'
    } else if (membership === 'premium') {
        amount = 2.00;
        productName = 'premium';
        productId = 'parton2'
    } else {
        console.log('다시 시도해주세요.')
    }
    //signature 값을 가져온다
    requestSignature()
    //파라미터 설정이 완료되면 '/payment'에 request 한다.
    paymentRequest();
}



//   결제가 관련 이벤트가 일어났을 때
window.addEventListener('message', function(event) {
    if(event.origin !== 'https://api.paymentwall.com') return;
    var eventData = JSON.parse(event.data);
    console.log('event')
    console.log(event);
    if (eventData.event == 'paymentSuccess') {
        // handle the successful payment scenario
        // alert('Thank you for paying ' + eventData.data.amount + ' ' + eventData.data.currency);
        console.log('결제 성공했습니다.');
        //pingback 요청
        requestPingback();
    }else if(eventData.event == 'widgetLoaded'){
        console.log('위젯이 로드되었습니다.')
    }else if(eventData.event == 'widgetSizeChanged'){
        console.log('위젯 사이즈가 변경되었습니다.')
    }else if(eventData.event == 'paymentProcessingStart'){
        console.log('결제 과정을 시작합니다.')
    }else if(eventData.event == 'paymentProcessingEnd'){
        console.log('결제 과정이 완료되었습니다.')
    }
},false);


