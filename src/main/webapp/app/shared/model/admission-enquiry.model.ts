import { Moment } from 'moment';

export interface IAdmissionEnquiry {
  id?: number;
  studentName?: string;
  studentMiddleName?: string;
  studentLastName?: string;
  cellPhoneNo?: string;
  landLinePhoneNo?: string;
  emailId?: string;
  dateOfBirth?: Moment;
  gender?: string;
  highestQualification?: string;
  modeOfEnquiry?: string;
  enquiryDate?: Moment;
  comments?: string;
  branchId?: number;
  departmentId?: number;
  courseId?: number;
  semesterId?: number;
  batchId?: number;
  stateId?: number;
  cityId?: number;
  academicYearId?: number;
  enquiryStatus?: string;
  createdBy?: string;
  createdOn?: Moment;
  updatedBy?: string;
  updatedOn?: Moment;
}

export const defaultValue: Readonly<IAdmissionEnquiry> = {};
