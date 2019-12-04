package com.synectiks.admission.filter.common;

import org.springframework.stereotype.Component;


@Component
public class CommonGraphiqlFilter {

//    private final Logger logger = LoggerFactory.getLogger(CommonGraphiqlFilter.class);
//
//    @Autowired
//    CollegeRepository collegeRepository;
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//
//    @Autowired
//    BranchRepository branchRepository;
//
//    @Autowired
//    SectionRepository sectionRepository;
//
//    @Autowired
//    BatchRepository batchRepository;
//
////    @Autowired
////    BranchService branchService;
//
//    public College getCollegeByName(String shortName) {
//        College cg = new College();
//        cg.setShortName(shortName);
//        Example<College> example = Example.of(cg);
//        Optional<College> newCG = collegeRepository.findOne(example);
//        if (newCG.isPresent()) {
//            return newCG.get();
//        }
//        return null;
//    }
//
//    public College getCollegeById(Long collegeId) {
//        College cg = new College();
//        cg.setId(collegeId);
//        Example<College> example = Example.of(cg);
//        Optional<College> newCG = collegeRepository.findOne(example);
//        if (newCG.isPresent()) {
//            return newCG.get();
//        }
//        return null;
//    }
//
//    public Branch getBranchById(Long branchId) {
//        Branch br = new Branch();
//        br.setId(branchId);
//        Example<Branch> example = Example.of(br);
//        Optional<Branch> newBr = branchRepository.findOne(example);
//        if (newBr.isPresent()) {
//            return newBr.get();
//        }
//        return null;
//    }
//
//    public Department getDepartmentById(Long departmentId){
//       Department dp =new Department();
//       dp.setId(departmentId);
//       Example<Department> example =Example.of(dp);
//       Optional<Department> newDp = departmentRepository.findById(departmentId);
//       if(newDp.isPresent()){
//           return newDp.get();
//       }
//       return null;
//    }
//    public Section getSectionById(Long sectionId){
//        Section sc =new Section();
//        sc.setId(sectionId);
//        Example<Section> example = Example.of(sc);
//        Optional<Section> newSC= sectionRepository.findById(sectionId);
//        if(newSC.isPresent()){
//            return newSC.get();
//        }
//        return null;
//    }
//    public Batch getBatchById(Long batchId){
//        Batch bt = new Batch();
//        bt.setId(batchId);
//        Example<Batch> example = Example.of(bt);
//        Optional<Batch> newBt = batchRepository.findById(batchId);
//        if(newBt.isPresent()){
//            return newBt.get();
//        }
//        return null;
//}
//
//        public List<Branch> getAllBranches(String branchName, Long collegeId){
//            return branchService.getAllBranches(branchName, collegeId);
//        }
    }


