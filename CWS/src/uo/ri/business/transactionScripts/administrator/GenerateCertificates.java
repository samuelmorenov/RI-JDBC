package uo.ri.business.transactionScripts.administrator;

import uo.ri.common.BusinessException;

public class GenerateCertificates {
	public int execute() throws BusinessException {
		int certificadosGenerados = 0;
		
		/* obtener mecanicos con:
		 * horas suficientes para cada tipo de vehiculo
		 * que no tiene certificado en ese tipo de vehiculo
		 
			

select M.id AS M_ID, 
SUM(C.hours * (D.percentage * 0.01)) AS HOURS_P, 
D.vehicletype_id AS V_ID,
V.mintraininghours as Min_H,
CER.id as CER_ID

from tmechanics as M 

join tenrollments as E
on m.id = E.mechanic_id

join tcourses as C
on E.course_id = C.id

join tdedications as D
on C.id = D.Course_id

join tvehicletypes as V
on D.vehicletype_id = V.id

full join tcertificates AS CER
on M.id = CER.mechanic_id

where e.passed = true

GROUP BY V_ID, M_ID, Min_H, CER_ID
ORDER BY M_ID, V_ID
		
		SELECT M.ID AS M_ID, SUM(C.HOURS * (D.PERCENTAGE * 0.01)) AS HOURS_P, D.VEHICLETYPE_ID AS V_ID, V.MINTRAININGHOURS AS MIN_H, CER.ID AS CER_ID FROM TMECHANICS AS M JOIN TENROLLMENTS AS E ON M.ID = E.MECHANIC_ID JOIN TCOURSES AS C ON E.COURSE_ID = C.ID JOIN TDEDICATIONS AS D ON C.ID = D.COURSE_ID JOIN TVEHICLETYPES AS V ON D.VEHICLETYPE_ID = V.ID FULL JOIN TCERTIFICATES AS CER ON M.ID = CER.MECHANIC_ID WHERE E.PASSED = TRUE GROUP BY V_ID, M_ID, MIN_H, CER_ID ORDER BY M_ID, V_ID

		
		*/
		//TODO Hacer la implementacion para la entrega
		return certificadosGenerados;
	}
}
