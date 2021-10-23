package MainRobot;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

public class RobotControl {
	
	 public static void main(String[] args) throws Exception {
	        Long timestamp = System.currentTimeMillis();
	        String secret = "SEC40ed8a11f01fe21ff9d537187542c79f21bbb524af707d9bc09aabcd1db97ac1";

	        String stringToSign = timestamp + "\n" + secret;
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
	        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
	        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
	        System.out.println(sign);
	        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d834f06998d3f25e828d677350c165a8be6fe65e5e16562a2e62251955f790a4"+"&timestamp="+timestamp+"&sign="+sign);
	        OapiRobotSendRequest request = new OapiRobotSendRequest();
	        request.setMsgtype("text");
	        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
	        text.setContent("Hello World!");
	        request.setText(text);
	        OapiRobotSendResponse response = client.execute(request);

	    }

}
