import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './admission-enquiry.reducer';
import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdmissionEnquiryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdmissionEnquiryUpdateState {
  isNew: boolean;
}

export class AdmissionEnquiryUpdate extends React.Component<IAdmissionEnquiryUpdateProps, IAdmissionEnquiryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { admissionEnquiryEntity } = this.props;
      const entity = {
        ...admissionEnquiryEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/admission-enquiry');
  };

  render() {
    const { admissionEnquiryEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.admissionEnquiry.home.createOrEditLabel">Create or edit a AdmissionEnquiry</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : admissionEnquiryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="admission-enquiry-id">ID</Label>
                    <AvInput id="admission-enquiry-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentNameLabel" for="admission-enquiry-studentName">
                    Student Name
                  </Label>
                  <AvField id="admission-enquiry-studentName" type="text" name="studentName" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentMiddleNameLabel" for="admission-enquiry-studentMiddleName">
                    Student Middle Name
                  </Label>
                  <AvField id="admission-enquiry-studentMiddleName" type="text" name="studentMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentLastNameLabel" for="admission-enquiry-studentLastName">
                    Student Last Name
                  </Label>
                  <AvField id="admission-enquiry-studentLastName" type="text" name="studentLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="cellPhoneNoLabel" for="admission-enquiry-cellPhoneNo">
                    Cell Phone No
                  </Label>
                  <AvField id="admission-enquiry-cellPhoneNo" type="text" name="cellPhoneNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="landLinePhoneNoLabel" for="admission-enquiry-landLinePhoneNo">
                    Land Line Phone No
                  </Label>
                  <AvField id="admission-enquiry-landLinePhoneNo" type="text" name="landLinePhoneNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailIdLabel" for="admission-enquiry-emailId">
                    Email Id
                  </Label>
                  <AvField id="admission-enquiry-emailId" type="text" name="emailId" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfBirthLabel" for="admission-enquiry-dateOfBirth">
                    Date Of Birth
                  </Label>
                  <AvField id="admission-enquiry-dateOfBirth" type="date" className="form-control" name="dateOfBirth" />
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel" for="admission-enquiry-gender">
                    Gender
                  </Label>
                  <AvField id="admission-enquiry-gender" type="text" name="gender" />
                </AvGroup>
                <AvGroup>
                  <Label id="highestQualificationLabel" for="admission-enquiry-highestQualification">
                    Highest Qualification
                  </Label>
                  <AvField id="admission-enquiry-highestQualification" type="text" name="highestQualification" />
                </AvGroup>
                <AvGroup>
                  <Label id="modeOfEnquiryLabel" for="admission-enquiry-modeOfEnquiry">
                    Mode Of Enquiry
                  </Label>
                  <AvField id="admission-enquiry-modeOfEnquiry" type="text" name="modeOfEnquiry" />
                </AvGroup>
                <AvGroup>
                  <Label id="enquiryDateLabel" for="admission-enquiry-enquiryDate">
                    Enquiry Date
                  </Label>
                  <AvField id="admission-enquiry-enquiryDate" type="date" className="form-control" name="enquiryDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="admission-enquiry-comments">
                    Comments
                  </Label>
                  <AvField
                    id="admission-enquiry-comments"
                    type="text"
                    name="comments"
                    validate={{
                      maxLength: { value: 5000, errorMessage: 'This field cannot be longer than 5000 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="branchIdLabel" for="admission-enquiry-branchId">
                    Branch Id
                  </Label>
                  <AvField id="admission-enquiry-branchId" type="string" className="form-control" name="branchId" />
                </AvGroup>
                <AvGroup>
                  <Label id="departmentIdLabel" for="admission-enquiry-departmentId">
                    Department Id
                  </Label>
                  <AvField id="admission-enquiry-departmentId" type="string" className="form-control" name="departmentId" />
                </AvGroup>
                <AvGroup>
                  <Label id="courseIdLabel" for="admission-enquiry-courseId">
                    Course Id
                  </Label>
                  <AvField id="admission-enquiry-courseId" type="string" className="form-control" name="courseId" />
                </AvGroup>
                <AvGroup>
                  <Label id="semesterIdLabel" for="admission-enquiry-semesterId">
                    Semester Id
                  </Label>
                  <AvField id="admission-enquiry-semesterId" type="string" className="form-control" name="semesterId" />
                </AvGroup>
                <AvGroup>
                  <Label id="batchIdLabel" for="admission-enquiry-batchId">
                    Batch Id
                  </Label>
                  <AvField id="admission-enquiry-batchId" type="string" className="form-control" name="batchId" />
                </AvGroup>
                <AvGroup>
                  <Label id="stateIdLabel" for="admission-enquiry-stateId">
                    State Id
                  </Label>
                  <AvField id="admission-enquiry-stateId" type="string" className="form-control" name="stateId" />
                </AvGroup>
                <AvGroup>
                  <Label id="cityIdLabel" for="admission-enquiry-cityId">
                    City Id
                  </Label>
                  <AvField id="admission-enquiry-cityId" type="string" className="form-control" name="cityId" />
                </AvGroup>
                <AvGroup>
                  <Label id="academicYearIdLabel" for="admission-enquiry-academicYearId">
                    Academic Year Id
                  </Label>
                  <AvField id="admission-enquiry-academicYearId" type="string" className="form-control" name="academicYearId" />
                </AvGroup>
                <AvGroup>
                  <Label id="enquiryStatusLabel" for="admission-enquiry-enquiryStatus">
                    Enquiry Status
                  </Label>
                  <AvField id="admission-enquiry-enquiryStatus" type="text" name="enquiryStatus" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdByLabel" for="admission-enquiry-createdBy">
                    Created By
                  </Label>
                  <AvField id="admission-enquiry-createdBy" type="text" name="createdBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdOnLabel" for="admission-enquiry-createdOn">
                    Created On
                  </Label>
                  <AvField id="admission-enquiry-createdOn" type="date" className="form-control" name="createdOn" />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="admission-enquiry-updatedBy">
                    Updated By
                  </Label>
                  <AvField id="admission-enquiry-updatedBy" type="text" name="updatedBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="admission-enquiry-updatedOn">
                    Updated On
                  </Label>
                  <AvField id="admission-enquiry-updatedOn" type="date" className="form-control" name="updatedOn" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/admission-enquiry" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  admissionEnquiryEntity: storeState.admissionEnquiry.entity,
  loading: storeState.admissionEnquiry.loading,
  updating: storeState.admissionEnquiry.updating,
  updateSuccess: storeState.admissionEnquiry.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionEnquiryUpdate);
