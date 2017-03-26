package com.kamontat.constance;

import java.util.*;

/**
 * Learn more at <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers">HTTP-Header</a>
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 26/Mar/2017 _ 9:47 PM
 */
public enum Headers {
	Accept,
	Accept_Charset,
	Accept_Encoding,
	Accept_Language,
	Accept_Ranges,
	Access_Control_Allow_Credentials,
	Access_Control_Allow_Headers,
	Access_Control_Allow_Methods,
	Access_Control_Allow_Origin,
	Access_Control_Expose_Headers,
	Access_Control_Max_Age,
	Access_Control_Request_Headers,
	Access_Control_Request_Method,
	Age,
	Allow,
	Authorization,
	Cache_Control,
	Connection,
	Content_Disposition,
	Content_Encoding,
	Content_Language,
	Content_Length,
	Content_Location,
	Content_Range,
	Content_Security_Policy,
	Content_Security_Policy_Report_Only,
	Content_Type,
	Cookie,
	Cookie2,
	DNT,
	Date,
	ETag,
	Expect,
	Expires,
	Forwarded,
	From,
	Host,
	If_Match,
	If_Modified_Since,
	If_None_Match,
	If_Range,
	If_Unmodified_Since,
	Keep_Alive,
	Large_Allocation,
	Last_Modified,
	Location,
	Origin,
	Pragma,
	Proxy_Authenticate,
	Proxy_Authorization,
	Public_Key_Pins,
	Public_Key_Pins_Report_Only,
	Range,
	Referer,
	Referrer_Policy,
	Retry_After,
	Server,
	Set_Cookie,
	Set_Cookie2,
	Strict_Transport_Security,
	TE,
	Tk,
	Trailer,
	Transfer_Encoding,
	Upgrade_Insecure_Requests,
	User_Agent,
	Vary,
	Via,
	WWW_Authenticate,
	Warning,
	X_Content_Type_Options,
	X_DNS_Prefetch_Control,
	X_Forwarded_For,
	X_Forwarded_Host,
	X_Forwarded_Proto,
	X_Frame_Options,
	X_XSS_Protection;
	
	@Override
	public String toString() {
		return this.name().toLowerCase(Locale.ENGLISH).replace("_", "-");
	}
}
