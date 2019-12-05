import { Moment } from 'moment';

export interface IAdmissionApplication {
  id?: number;
  sourceOfApplication?: string;
  studentId?: number;
  applicationDate?: Moment;
  completionDate?: Moment;
  admissionNo?: number;
  admissionDate?: Moment;
  comments?: string;
  applicationStatus?: string;
  branchId?: number;
  createdBy?: string;
  createdOn?: Moment;
  updatedBy?: string;
  updatedOn?: Moment;
  admissionEnquiryId?: number;
}

export const defaultValue: Readonly<IAdmissionApplication> = {};
