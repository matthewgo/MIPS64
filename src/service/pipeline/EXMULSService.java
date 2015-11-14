package service.pipeline;

import java.util.NoSuchElementException;

import Model.Instruction;
import Model.InternalRegister;
import controller.PipelineMapController;
import service.RevisedPipelineService;

public class EXMULSService extends PipelineFunction {

	public EXMULSService(InternalRegister ir, RevisedPipelineService pipelineService) {
		super(ir, pipelineService);
	}

	@Override
	public void run(int cycleNumber) {
		try {
			if (queue.peekFirst() != null && queue.peekFirst().isIdFinished()) {
				Instruction ins = queue.remove();

				String cond = "0";
				String aluOutput;
				Float a = Float.parseFloat(ir.getIDEXA());
				Float b = Float.parseFloat(ir.getIDEXB());
				aluOutput = Float.toString(a * b);

				ir.setEXMEMALUOutputMULS(aluOutput);
				ir.setEXMEMCondMULS(cond);
				ir.setEXMEMIRMULS(ir.getIDEXIR());
				ir.setEXMEMBMULS(ir.getIDEXB());

				PipelineMapController.setMapValue("EXMULS", ins.getLineNumber(), cycleNumber);
				ins.setExFinished(ins.getExFinished() + 1);
				if (ins.getExFinished() == 6) {
					pipelineService.addInstructionTo("MEM", ins.getLineNumber());
					pipelineService.addInstructionTo("WB", ins.getLineNumber());
				}
			}
		} catch (NoSuchElementException e) {

		}
	}

}
