package springbootCRUD.springbootCRUD;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collections;
import java.util.Scanner;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.snmp4j.transport.DefaultUdpTransportMapping;


@SpringBootApplication
public class SpringbootCrudApplication {
 
	Snmp snmp = null;
	String address = null;

	public  SpringbootCrudApplication()
	{
		
	}
	
	public SpringbootCrudApplication(String add) {
		address = add;
	}
	
	public static void main(String[] args) throws IOException{
		SpringApplication.run(SpringbootCrudApplication.class, args);
		
		
		
		SpringbootCrudApplication client = new SpringbootCrudApplication("udp:127.0.0.1/161");
		client.start();
		
		String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
		System.out.println("System Desc: "+sysDescr);
		String sysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
		System.out.println("System Name: "+sysName);		 
		String sysObjectId = client.getAsString(new OID(".1.3.6.1.2.1.1.2.0"));
		System.out.println("System ObjectId: "+sysObjectId);
		String sysTime = client.getAsString(new OID(".1.3.6.1.2.1.1.3.0"));
		System.out.println("System Time: "+sysTime);
		String sysLocation = client.getAsString(new OID(".1.3.6.1.2.1.1.6.0"));
		System.out.println("System Location: "+sysLocation);		
		
		
	}
	public void start() throws IOException {
		TransportMapping<?> transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);
		transport.listen();
	}

	public String getAsString(OID oid) throws IOException {
		ResponseEvent<Address> event = get(new OID[] { oid });
		return event.getResponse().get(0).getVariable().toString();
	}

	
	public ResponseEvent<Address> get(OID oids[]) throws IOException {
		PDU pdu = new PDU();
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		pdu.setType(PDU.GET);
		ResponseEvent<Address> event = snmp.send(pdu, getTarget(), null);
		if (event != null) {
			return event;
		}
		throw new RuntimeException("GET timed out");
	}

	private Target<Address> getTarget() {
		Address targetAddress = GenericAddress.parse(address);
		CommunityTarget<Address> target = new CommunityTarget<Address>();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

}



	
