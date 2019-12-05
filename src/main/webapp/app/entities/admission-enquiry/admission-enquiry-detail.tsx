import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admission-enquiry.reducer';
import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionEnquiryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdmissionEnquiryDetail extends React.Component<IAdmissionEnquiryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { admissionEnquiryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdmissionEnquiry [<b>{admissionEnquiryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentName">Student Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentName}</dd>
            <dt>
              <span id="studentMiddleName">Student Middle Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentMiddleName}</dd>
            <dt>
              <span id="studentLastName">Student Last Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentLastName}</dd>
            <dt>
              <span id="cellPhoneNo">Cell Phone No</span>
            </dt>
            <dd>{admissionEnquiryEntity.cellPhoneNo}</dd>
            <dt>
              <span id="landLinePhoneNo">Land Line Phone No</span>
            </dt>
            <dd>{admissionEnquiryEntity.landLinePhoneNo}</dd>
            <dt>
              <span id="emailId">Email Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.emailId}</dd>
            <dt>
              <span id="dateOfBirth">Date Of Birth</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="gender">Gender</span>
            </dt>
            <dd>{admissionEnquiryEntity.gender}</dd>
            <dt>
              <span id="highestQualification">Highest Qualification</span>
            </dt>
            <dd>{admissionEnquiryEntity.highestQualification}</dd>
            <dt>
              <span id="modeOfEnquiry">Mode Of Enquiry</span>
            </dt>
            <dd>{admissionEnquiryEntity.modeOfEnquiry}</dd>
            <dt>
              <span id="enquiryDate">Enquiry Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.enquiryDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{admissionEnquiryEntity.comments}</dd>
            <dt>
              <span id="branchId">Branch Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.branchId}</dd>
            <dt>
              <span id="departmentId">Department Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.departmentId}</dd>
            <dt>
              <span id="courseId">Course Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.courseId}</dd>
            <dt>
              <span id="semesterId">Semester Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.semesterId}</dd>
            <dt>
              <span id="batchId">Batch Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.batchId}</dd>
            <dt>
              <span id="stateId">State Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.stateId}</dd>
            <dt>
              <span id="cityId">City Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.cityId}</dd>
            <dt>
              <span id="academicYearId">Academic Year Id</span>
            </dt>
            <dd>{admissionEnquiryEntity.academicYearId}</dd>
            <dt>
              <span id="enquiryStatus">Enquiry Status</span>
            </dt>
            <dd>{admissionEnquiryEntity.enquiryStatus}</dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{admissionEnquiryEntity.createdBy}</dd>
            <dt>
              <span id="createdOn">Created On</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{admissionEnquiryEntity.updatedBy}</dd>
            <dt>
              <span id="updatedOn">Updated On</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/admission-enquiry" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/admission-enquiry/${admissionEnquiryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ admissionEnquiry }: IRootState) => ({
  admissionEnquiryEntity: admissionEnquiry.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionEnquiryDetail);
