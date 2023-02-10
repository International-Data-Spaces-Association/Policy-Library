package de.fraunhofer.iese.ids.odrl.policy.library.model.interfaces;

import java.net.URI;
import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.Party;
import de.fraunhofer.iese.ids.odrl.policy.library.model.Rule;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PolicyType;

public interface IPolicy {
	List<Rule> getRules();
	void setRules(List<Rule> rules);

	public URI getProfile();
	public void setProfile(URI profile);

	public URI getPolicyId();
	public void setPolicyId(URI policyId);

	public PolicyType getType();
	public void setType(PolicyType type);

	public Party getConsumer();
	public void setConsumer(Party consumer);
	
	public Party getProvider();
	public void setProvider(Party provider);

	public boolean isProviderSide();
	public void setProviderSide(boolean providerSide);
}
