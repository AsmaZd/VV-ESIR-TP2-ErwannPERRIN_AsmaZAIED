# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

A refresher on TCC and LCC is available in the [course notes](https://oscarlvp.github.io/vandv-classes/#cohesion-graph).

## Answer

TTC et LCC ne peuvent avoir la même valeur que lorsque les classes sont reliées entre elles uniquement par des liens directs.

Exemple :

Class Car{
	private Motor m_motor;
	private Wheel m_wheel;

	public string getSpecs(){ return str(m_motor) + str(m_wheel);}

	public void run(){
		m_wheel.activate();
		m_motot.activate();
	}
	public void stop(){
		m_wheel.deactivate();
		m_motot.deactivate();		
	}
}

Un contre exemple serait :

Class Car{
	private Motor m_motor;
	private Wheel m_wheel;

	public void getMotor(){ return m_motor;}
	public void getWheel(){return m_wheel;}

	public void run(){
		m_motor.activate();
	}

	public void move(){
		if (m_motor.isActivated()){
			m wheel.activate()
		}
	}

	public void stop(){
		m_wheel.deactivate();
		m_motot.deactivate();		
	}
}


LCC ne peut jamais être plus faible que TCC car par définition, LCC est la somme des connexions directes (TCC) et des connexions indirectes. Même si les suivantes sont nulles on aura toujours LCC = TCC + 0 qui n’est jamais plus faible que TCC.